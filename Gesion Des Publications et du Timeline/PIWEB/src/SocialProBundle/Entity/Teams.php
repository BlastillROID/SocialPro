<?php

namespace SocialProBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Teams
 *
 * @ORM\Table(name="teams", indexes={@ORM\Index(name="leader_id", columns={"leader_id"}), @ORM\Index(name="leader_id_2", columns={"leader_id"}), @ORM\Index(name="timeline_id", columns={"timeline_id"})})
 * @ORM\Entity
 */
class Teams
{
    /**
     * @var integer
     *
     * @ORM\Column(name="team_id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $teamId;

    /**
     * @var string
     *
     * @ORM\Column(name="team_name", type="string", length=50, nullable=false)
     */
    private $teamName;

    /**
     * @var \Employee
     *
     * @ORM\ManyToOne(targetEntity="Employee")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="leader_id", referencedColumnName="id")
     * })
     */
    private $leader;

    /**
     * @var \Timeline
     *
     * @ORM\ManyToOne(targetEntity="Timeline")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="timeline_id", referencedColumnName="timeline_id")
     * })
     */
    private $timeline;


}

