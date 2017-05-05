<?php

namespace SocialProBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * SubProjects
 *
 * @ORM\Table(name="sub_projects", indexes={@ORM\Index(name="project_id", columns={"project_id"}), @ORM\Index(name="team_id", columns={"team_id"})})
 * @ORM\Entity
 */
class SubProjects
{
    /**
     * @var integer
     *
     * @ORM\Column(name="sub_project_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $subProjectId;

    /**
     * @var string
     *
     * @ORM\Column(name="sub_project_name", type="string", length=50, nullable=false)
     */
    private $subProjectName;

    /**
     * @var \Projects
     *
     * @ORM\ManyToOne(targetEntity="Projects")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="project_id", referencedColumnName="project_id")
     * })
     */
    private $project;

    /**
     * @var \Teams
     *
     * @ORM\ManyToOne(targetEntity="Teams")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="team_id", referencedColumnName="team_id")
     * })
     */
    private $team;


}

