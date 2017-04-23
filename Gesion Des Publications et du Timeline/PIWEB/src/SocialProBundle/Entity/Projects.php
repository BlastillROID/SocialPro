<?php

namespace SocialProBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Projects
 *
 * @ORM\Table(name="projects", indexes={@ORM\Index(name="manager_id", columns={"manager_id"})})
 * @ORM\Entity
 */
class Projects
{
    /**
     * @var integer
     *
     * @ORM\Column(name="project_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $projectId;

    /**
     * @var string
     *
     * @ORM\Column(name="project_name", type="string", length=50, nullable=false)
     */
    private $projectName;

    /**
     * @var string
     *
     * @ORM\Column(name="project_desc", type="text", length=65535, nullable=false)
     */
    private $projectDesc;

    /**
     * @var \Employee
     *
     * @ORM\ManyToOne(targetEntity="Employee")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="manager_id", referencedColumnName="id")
     * })
     */
    private $manager;


}

